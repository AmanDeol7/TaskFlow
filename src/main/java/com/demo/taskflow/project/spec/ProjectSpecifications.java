package com.demo.taskflow.project.spec;

import com.demo.taskflow.project.Project;
import com.demo.taskflow.user.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ProjectSpecifications {
    public static Specification<Project> ownedBy(User owner){
      return  (root, query, cb) ->

            cb.equal(root.get("owner"), owner);

    }

    public static Specification <Project> nameContains( String name){
        return (root, query, cb) ->{
            if( name == null || name.isEmpty() ){
                return cb.conjunction();
            }

           return cb.like( cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        } ;


    }

    public static Specification <Project> createdBetween(LocalDateTime from , LocalDateTime to){
        return (root, query,cb)-> {

            if (from == null && to == null) {
                return cb.conjunction();
            }
            if (from == null) {
                return cb.lessThanOrEqualTo(root.get("createdAt"), to);
            }
            if (to == null) {
                return cb.greaterThanOrEqualTo(root.get("createdAt"), from);
            }
            return cb.between(root.get("createdAt"),from , to );
        };
    }


}
