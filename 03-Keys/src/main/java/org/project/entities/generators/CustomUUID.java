package org.project.entities.generators;

import org.hibernate.annotations.IdGeneratorType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IdGeneratorType(CustomUUIDGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomUUID {
}
