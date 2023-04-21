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
package net.sf.cglib.transform;

import net.sf.cglib.core.Constants;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.TypePath;

public class FieldVisitorTee extends FieldVisitor {
    private FieldVisitor fv1, fv2;
    
    public FieldVisitorTee(FieldVisitor fv1, FieldVisitor fv2) {
	super(Constants.ASM_API);
	this.fv1 = fv1;
        this.fv2 = fv2;
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return AnnotationVisitorTee.getInstance(fv1.visitAnnotation(desc, visible),
                                                fv2.visitAnnotation(desc, visible));
    }
    
    public void visitAttribute(Attribute attr) {
        fv1.visitAttribute(attr);
        fv2.visitAttribute(attr);
    }

    public void visitEnd() {
        fv1.visitEnd();
        fv2.visitEnd();
    }

    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        return AnnotationVisitorTee.getInstance(fv1.visitTypeAnnotation(typeRef, typePath, desc, visible),
                                                fv2.visitTypeAnnotation(typeRef, typePath, desc, visible));
    }
}

