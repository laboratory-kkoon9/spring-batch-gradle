package com.laboratorykkoon9.springbatchgradle.global.common;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import javax.persistence.*;
import java.time.*;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    protected LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_user_id", updatable = false)
    protected Long createdUserId;

    @LastModifiedBy
    @Column(name = "updated_user_id", insertable = false)
    protected Long updatedUserId;
}
