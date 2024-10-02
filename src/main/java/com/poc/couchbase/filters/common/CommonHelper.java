package com.poc.couchbase.filters.common;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonHelper {
  private CommonHelper() {}

  /**
   * Dynamically build the mongoDB Sort object based on String input with comma separated field
   * names.
   *
   * @param feed multiple values like String feed = "fieldName"; Resulting Sort:
   *             Sort.by(Sort.Order.asc("fieldName"))
   *             <p>String feed = "fieldName desc"; Resulting Sort: Sort.by(Sort.Order.desc("fieldName"))
   *             <p>String feed = "field1, field2, field3"; Resulting Sort:
   *             Sort.by(Sort.Order.asc("field1"), Sort.Order.asc("field2"), Sort.Order.asc("field3"))
   *             String feed = "field1 desc, field2, field3 asc"; Resulting Sort:
   *             Sort.by(Sort.Order.desc("field1"), Sort.Order.asc("field2"), Sort.Order.asc("field3"))
   *             <p>String feed = ""; Resulting Sort: null (since input is empty)
   *             <p>String feed = null; Resulting Sort: null (since input is null)
   *             <p>String feed = " "; Resulting Sort: null (since input contains only whitespace)
   *             <p>String feed = "field1 DeSc, field2 aSc, field3 ASC"; Resulting Sort:
   *             Sort.by(Sort.Order.desc("field1"), Sort.Order.asc("field2"), Sort.Order.asc("field3"))
   * @return sort object
   */
  public static Sort sortBy(String feed) {
    if (feed==null || feed.isEmpty()) {
      return null;
    }

    feed = feed
            .trim()
            .replaceAll("\\s{2,}", " ");
    List<Sort.Order> sortOrder = new ArrayList<>();

    Pattern pattern = Pattern.compile("(\\S+)\\s*(desc|asc)?", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(feed);

    while (matcher.find()) {
      String field = matcher.group(1);
      String direction = matcher.group(2);

      Sort.Direction sortDirection =
              (direction!=null && direction.equalsIgnoreCase("desc"))
                      ? Sort.Direction.DESC
                      : Sort.Direction.ASC;

      sortOrder.add(new Sort.Order(sortDirection, field));
    }

    return Sort.by(sortOrder);
  }
}
