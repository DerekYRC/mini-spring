/*
 * Copyright 2003,2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.cglib.transform;

import net.sf.cglib.core.CodeGenerationException;
import net.sf.cglib.core.ClassGenerator;
import net.sf.cglib.core.DebuggingClassWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Attribute;

import java.io.IOException;

abstract public class AbstractClassLoader extends ClassLoader {
    private ClassFilter filter;
    private ClassLoader classPath;
    private static java.security.ProtectionDomain DOMAIN ;
    
    static{
        
        DOMAIN = (java.security.ProtectionDomain)
        java.security.AccessController.doPrivileged(
          new java.security.PrivilegedAction() {
            public Object run() {
               return AbstractClassLoader.class.getProtectionDomain();
            }
        }); 
     }
    
    protected AbstractClassLoader(ClassLoader parent, ClassLoader classPath, ClassFilter filter) {
        super(parent);
        this.filter = filter;
        this.classPath = classPath;
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        
        Class loaded = findLoadedClass(name);
        
        if( loaded != null ){
            if( loaded.getClassLoader() == this ){
               return loaded;
            }//else reload with this class loader
        }
        
        if (!filter.accept(name)) {
            return super.loadClass(name);
        }
        ClassReader r;
        try {
            
           java.io.InputStream is = classPath.getResourceAsStream( 
                       name.replace('.','/') + ".class"
                  ); 
           
           if (is == null) {
               
              throw new ClassNotFoundException(name);
              
           }
           try { 
               
              r = new ClassReader(is);
            
           } finally {
               
              is.close();
             
           }
        } catch (IOException e) {
            throw new ClassNotFoundException(name + ":" + e.getMessage());
        }

        try {
            DebuggingClassWriter w = 
        	    new DebuggingClassWriter(ClassWriter.COMPUTE_FRAMES);
            getGenerator(r).generateClass(w);
            byte[] b = w.toByteArray();
            Class c = super.defineClass(name, b, 0, b.length, DOMAIN);
            postProcess(c);
            return c;
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e) {
            throw e;
        } catch (Exception e) {
            throw new CodeGenerationException(e);
        }
    }

    protected ClassGenerator getGenerator(ClassReader r) {
        return new ClassReaderGenerator(r, attributes(), getFlags());
    }

    protected int getFlags() {
        return 0;
    }
    
    protected Attribute[] attributes() {
        return null;
    }

    protected void postProcess(Class c) {
    }
}
