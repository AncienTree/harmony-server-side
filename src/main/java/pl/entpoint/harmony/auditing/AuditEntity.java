package pl.entpoint.harmony.auditing;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mateusz Dąbek
 * @created 24 kwi 2020
 * 
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public abstract class AuditEntity {
	
	@CreatedDate
	@Column(name = "created_date", nullable = false)
    @JsonIgnore
    private Instant createdDate;
	
    @LastModifiedDate
    @Column(name = "last_modified_date",nullable = false)
    @JsonIgnore
    private Instant lastModifiedDate;
    
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    @JsonIgnore
    private String createdBy;
    
    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false)
    @JsonIgnore
    private String lastModifiedBy;

}
