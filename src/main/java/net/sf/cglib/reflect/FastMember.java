/*
 * Copyright 2003 The Apache Software Foundation
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
package net.sf.cglib.reflect;

import java.lang.reflect.Member;

abstract public class FastMember
{
    protected FastClass fc;
    protected Member member;
    protected int index;

    protected FastMember(FastClass fc, Member member, int index) {
        this.fc = fc;
        this.member = member;
        this.index = index;
    }

    abstract public Class[] getParameterTypes();
    abstract public Class[] getExceptionTypes();

    public int getIndex() {
        return index;
    }

    public String getName() {
        return member.getName();
    }

    public Class getDeclaringClass() {
        return fc.getJavaClass();
    }

    public int getModifiers() {
        return member.getModifiers();
    }

    public String toString() {
        return member.toString();
    }

    public int hashCode() {
        return member.hashCode();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof FastMember)) {
            return false;
        }
        return member.equals(((FastMember)o).member);
    }
}
