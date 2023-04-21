/*
 * Copyright 2004 The Apache Software Foundation
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
package net.sf.cglib.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import net.sf.cglib.core.CollectionUtils;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.RejectModifierPredicate;
import org.objectweb.asm.ClassVisitor;

/**
 * @author Chris Nokleberg
 * @version $Id: MixinEverythingEmitter.java,v 1.3 2004/06/24 21:15:19 herbyderby Exp $
 */
class MixinEverythingEmitter extends MixinEmitter {

    public MixinEverythingEmitter(ClassVisitor v, String className, Class[] classes) {
        super(v, className, classes, null);
    }

    protected Class[] getInterfaces(Class[] classes) {
        List list = new ArrayList();
        for (int i = 0; i < classes.length; i++) {
            ReflectUtils.addAllInterfaces(classes[i], list);
        }
        return (Class[])list.toArray(new Class[list.size()]);
    }

    protected Method[] getMethods(Class type) {
        List methods = new ArrayList(Arrays.asList(type.getMethods()));
        CollectionUtils.filter(methods, new RejectModifierPredicate(Modifier.FINAL | Modifier.STATIC));
        return (Method[])methods.toArray(new Method[methods.size()]);
    }
}
