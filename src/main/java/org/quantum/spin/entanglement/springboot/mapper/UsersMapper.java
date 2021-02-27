package org.quantum.spin.entanglement.springboot.mapper;

import org.apache.ibatis.annotations.Select;
import org.quantum.spin.entanglement.springboot.domain.users.Users;

import java.util.Optional;

public interface UsersMapper {

        public Optional<Users> findById(long id);

        @Select("SELECT * FROM users where id = #{id}")
        public Optional<Users> findById2(long id);

}
