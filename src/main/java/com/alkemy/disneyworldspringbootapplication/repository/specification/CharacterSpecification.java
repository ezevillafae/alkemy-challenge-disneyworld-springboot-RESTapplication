package com.alkemy.disneyworldspringbootapplication.repository.specification;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterFilterDto;
import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collections;
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

            if(StringUtils.hasLength(filterDto.getId())){
                predicateList.add(
                        criteriaBuilder.equal(
                                root.get("id"),
                                filterDto.getId()
                        )
                );
            }

            if(StringUtils.hasLength(filterDto.getAge())){
                predicateList.add(
                        criteriaBuilder.equal(
                                root.get("age"),
                                filterDto.getAge()
                        )
                );
            }

            if(!CollectionUtils.isEmpty(filterDto.getMovies())){
                Join<FilmEntity, CharacterEntity> join = root.join("films", JoinType.INNER);
                Expression<String> filmsId = join.get("id");
                predicateList.add(filmsId.in(filterDto.getMovies()));
            }

            query.distinct(true);


            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
