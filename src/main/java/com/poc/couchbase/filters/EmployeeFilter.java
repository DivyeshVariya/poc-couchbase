package com.poc.couchbase.filters;

import com.couchbase.client.core.deps.com.google.common.base.Strings;
import com.poc.couchbase.filters.common.DefaultFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.QueryCriteria;


@Slf4j
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EmployeeFilter extends DefaultFilter {
  private String employeeId;
  private String email;
  private String phoneNumber;
  private String firstName;

  @Override
  public Query toQuery() {
    Query query = super.toQuery();

    addEmployeeIdCriteria(query);
    addFirstNameCriteria(query);
    addEmailCriteria(query);
    addPhoneNumberCriteria(query);

    return query;
  }

  private void addFirstNameCriteria(Query query) {
    log.trace("Inside addFirstNameCriteria Method.");
    if (Strings.isNullOrEmpty(firstName)) {
      query.addCriteria(QueryCriteria.where("firstName").regex(firstName));
      log.trace("Firstname criteria added.");
    }
  }

  private void addEmailCriteria(Query query) {
    log.trace("Inside addEmailCriteria Method.");
    if (Strings.isNullOrEmpty(email)) {
      query.addCriteria(QueryCriteria
              .where("email")
              .is(email));
      log.trace("Email criteria added.");
    }
  }

  private void addPhoneNumberCriteria(Query query) {
    log.trace("Inside addPhoneNumberCriteria Method.");
    if (Strings.isNullOrEmpty(phoneNumber)) {
      query.addCriteria(QueryCriteria
              .where("phoneNumber")
              .is(phoneNumber));
      log.trace("PhoneNumber criteria added.");
    }
  }

  private void addEmployeeIdCriteria(Query query) {
    log.trace("Inside addEmployeeIdCriteria Method.");
    if (Strings.isNullOrEmpty(employeeId)) {
      query.addCriteria(QueryCriteria
              .where("meta.id")
              .is(employeeId));
      log.trace("EmployeeId criteria added.");
    }
  }

}
