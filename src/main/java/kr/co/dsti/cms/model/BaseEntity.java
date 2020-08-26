package kr.co.dsti.cms.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate;
    
    @Column(length=50)
    private String createdBy;
    
    private LocalDateTime updateDate;
    
    @Column(length=50)
    private String updatedBy;

    private boolean isDelete;

    public boolean isNew() {
        return this.id == null;
    }
}
