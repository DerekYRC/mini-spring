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
package net.sf.cglib.core;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.Type;

abstract public class ClassInfo {

    protected ClassInfo() {
    }

    abstract public Type getType();
    abstract public Type getSuperType();
    abstract public Type[] getInterfaces();
    abstract public int getModifiers();

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof ClassInfo))
            return false;
        return getType().equals(((ClassInfo)o).getType());
    }

    public int hashCode() {
        return getType().hashCode();
    }

    public String toString() {
        // TODO: include modifiers, superType, interfaces
        return getType().getClassName();
    }
}
