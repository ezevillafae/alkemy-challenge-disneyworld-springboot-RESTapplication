package com.alkemy.disneyworldspringbootapplication.repository.specification;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterFilterDto;
import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if(StringUtils.hasLength(filterDto.getName())){
                predicateList.add(
                        criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                            "%" + filterDto.getName().toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
