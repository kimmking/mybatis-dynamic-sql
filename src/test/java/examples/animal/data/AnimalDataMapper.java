/**
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package examples.animal.data;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.dynamic.sql.delete.render.DeleteSupport;
import org.mybatis.dynamic.sql.insert.render.InsertSupport;
import org.mybatis.dynamic.sql.select.render.SelectSupport;
import org.mybatis.dynamic.sql.update.render.UpdateSupport;

public interface AnimalDataMapper {

    @Select({
        "${fullSelectStatement}"
    })
    List<AnimalData> selectMany(SelectSupport selectSupport);

    @Select({
        "${fullSelectStatement}"
    })
    Integer selectAnInteger(SelectSupport selectSupport);
    
    @Delete({
        "${fullDeleteStatement}"
    })
    int delete(DeleteSupport deleteSupport);

    @Update({
        "${fullUpdateStatement}"
    })
    int update(UpdateSupport updateSupport);
    
    @Insert({
        "${fullInsertStatement}"
    })
    int insert(InsertSupport<AnimalData> insertSupport);
}
