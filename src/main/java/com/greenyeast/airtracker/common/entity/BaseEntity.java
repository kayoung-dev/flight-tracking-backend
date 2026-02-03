package com.greenyeast.airtracker.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // PK, 고유 ID

//    @Column(name = "created_at", nullable = false, updatable = false)
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;  // 생성일
    
    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;  // 수정일

    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt;  // Row 삭제일

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }

    



}
