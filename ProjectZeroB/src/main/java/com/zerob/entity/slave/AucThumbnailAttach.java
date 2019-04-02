package com.zerob.entity.slave;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name="AUC_THUMBNAIL_ATTACH")
public class AucThumbnailAttach {

    @Id
	@Column(name="V_RECORDID")
	private String vrecordid;
	
	@Column(name="V_FILEID")
	private String vfieldid;
	
}
