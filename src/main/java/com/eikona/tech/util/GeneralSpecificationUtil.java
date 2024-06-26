package com.eikona.tech.util;

import static com.eikona.tech.constants.ApplicationConstants.IS_DELETED;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.eikona.tech.constants.ApplicationConstants;
import com.eikona.tech.constants.NumberConstants;

@Component
public class GeneralSpecificationUtil<T> {
	
	public Specification<T> objectSpecification(Object obj, String field) {
		return (root, query, cb) -> {
			return cb.equal(root.get(field), obj);
		};
	}
	
	public Specification<T> isNullSpecification(String field) {
		return (root, query, cb) -> {
			if (field == null || field.isEmpty()) {
				return cb.conjunction();
			}
			return cb.isNull(root.get(field));
		};
	}
	
	public Specification<T> isNotNullSpecification(String field) {
		return (root, query, cb) -> {
			if (field == null || field.isEmpty()) {
				return cb.conjunction();
			}
			return cb.isNotNull(root.get(field));
		};
	}
	
	public Specification<T> booleanSpecification(boolean value, String field) {
		return (root, query, cb) -> {
			return cb.equal(root.get(field), value);
		};
	}
	
	public Specification<T> greaterThanSpecification(int value, String field) {
		return (root, query, cb) -> {
			return cb.greaterThan(root.get(field), value);
		};
	}
	
	public Specification<T> greaterThanSpecification(String value, String field) {
		return (root, query, cb) -> {

			return cb.greaterThan(root.get(field), value);
		};
	}
	
	public Specification<T> greaterThanOrEqualToSpecification(Date value, String field) {
		return (root, query, cb) -> {

			if (null == value) {
				return cb.conjunction();
			}
			return cb.greaterThanOrEqualTo(root.<Date>get(field), value);
		};
	}
	
	public Specification<T> lessThanOrEqualToSpecification(Date value, String field) {
		return (root, query, cb) -> {

			if (null == value) {
				return cb.conjunction();
			}
			return cb.lessThanOrEqualTo(root.<Date>get(field), value);
		};
	}
	
	
	public Specification<T> isDeletedSpecification() {
		return (root, query, cb) -> {
			return cb.equal(root.get(IS_DELETED), false);
		};
	}
	
	public Specification<T> dateSpecification(Date startDate, Date endDate, String field) {
		return (root, query, cb) -> {
			if (null == startDate && null == endDate) {
				return cb.conjunction();
			}
			return cb.between(root.<Date>get(field), startDate, endDate);
		};
	}

	public Specification<T> longSpecification(Long value, String field) {
		return (root, query, cb) -> {
			if (null == value) {
				return cb.conjunction();
			}
			return cb.equal(root.get(field), value);
		};
	}
	
	public Specification<T> stringSpecification(String value, String field) {
		return (root, query, cb) -> {
			if (value == null || value.isEmpty()) {
				return cb.conjunction();
			}
			return cb.like(cb.lower(root.<String>get(field)), ApplicationConstants.DELIMITER_PERCENTAGE + value + ApplicationConstants.DELIMITER_PERCENTAGE);
		};
	}
	
	
	public Specification<T> stringNotSpecification(String value, String field) {
		return (root, query, cb) -> {
			if (value == null || value.isEmpty()) {
				return cb.conjunction();
			}
			return cb.notEqual(root.get(field), value);
		};
	}
	
	public Specification<T> foreignKeyLongSpecification(String value, String obj, String field) {
		return (root, query, cb) -> {
			if (null == value) {
				return cb.conjunction();
			}
			return cb.equal(root.get(obj).get(field), value);
		};
	}
	
	public Specification<T> foreignKeyStringSpecification(String value, String obj, String field){
		return (root, query, cb) -> {
			if (value == null || value.isEmpty() ||  NumberConstants.STRING_ZERO.equalsIgnoreCase(value)) {
				return cb.conjunction();
			}
			return cb.like(cb.lower(root.get(obj).get(field)), ApplicationConstants.DELIMITER_PERCENTAGE + value + ApplicationConstants.DELIMITER_PERCENTAGE);
		};
	}
	public Specification<T> foreignKeyDoubleObjectStringSpecification(String value, String obj,String secondObj, String field){
		return (root, query, cb) -> {
			if (value == null || value.isEmpty() || NumberConstants.STRING_ZERO.equalsIgnoreCase(value)) {
				return cb.conjunction();
			}
			return cb.like(cb.lower(root.get(obj).get(secondObj).get(field)), ApplicationConstants.DELIMITER_PERCENTAGE + value + ApplicationConstants.DELIMITER_PERCENTAGE);
		};
	}
	
	public Specification<T> foreignKeyListStringSpecification(List<String> value, String obj, String field) {
		
		return (Specification<T>) (root, query, cb) -> {
			if(value.isEmpty()) {
				return cb.conjunction();
			}
			return root.get(obj).get(field).in(value);
		};
	}

	public Specification<T> foreignKeyLongSpecification(Long value, String obj, String field) {

		return (Specification<T>) (root, query, cb) -> {
			if(null == value) {
				return cb.conjunction();
			}
			return cb.equal(root.get(obj).get(field), value);
		};
	}
	public Specification<T> foreignKeyTripleSpecification(String value, String obj, String secondObj, String thirdObj, String field){
		return (root, query, cb) -> {
			if (value == null || value.isEmpty() || NumberConstants.STRING_ZERO.equalsIgnoreCase(value)) {
				return cb.conjunction();
			}
			return cb.like(cb.lower(root.join(obj).join(secondObj).join(thirdObj).get(field)), ApplicationConstants.DELIMITER_PERCENTAGE + value + ApplicationConstants.DELIMITER_PERCENTAGE);
		};
	}

	public Specification<T> stringEqualSpecification(String value, String field) {
		return (root, query, cb) -> {
			if (value == null || value.isEmpty()) {
				return cb.conjunction();
			}
			return cb.equal(cb.lower(root.<String>get(field)), value);
		};
	}
	
}