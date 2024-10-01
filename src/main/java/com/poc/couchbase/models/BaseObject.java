package com.poc.couchbase.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.couchbase.core.index.QueryIndexed;

import java.util.Date;
import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseObject {

  /**
   * Holds a SystemUserId detected from the token for the Audit Purpose.
   */
  @CreatedBy
  private String createdBy;

  /**
   * Holds a SystemUserId detected from the token for the Audit Purpose.
   */
  @LastModifiedBy
  private String lastUpdatedBy;

  /**
   * UTC time-stamp of the Create Date in case of CRUD operations.
   */
  @CreatedDate
  @QueryIndexed
  private Date createdOn;

  /**
   * UTD time-stamp of the Edit or Delete Operations in Case of CRUN Operation.
   */
  @LastModifiedDate
  private Date lastUpdatedOn;

  /**
   * @implNote the status of object whether active or inactive.
   */
  @QueryIndexed
  private boolean inactive;

  /**
   * @implNote - Soft Deletion flag. It may require to just execute soft delete instead of physical
   * delete operation. This flag indicates whether the object/document is deleted or not.
   */
  @QueryIndexed
  private boolean deleted;

  /**
   * @implNote : To display in the Audit Information. (i.e. Reason for Change while edit or delete)
   * This is options field.
   * @since : 3.0
   */
  private String remark;

  /**
   * Any other non-standard information that is needed to be included with object for display
   * purpose only.
   */
  private Map<String, Object> metaInfo;
}
