package com.onlineStore.store.repos;

import com.onlineStore.store.domain.ReplyMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReplyMessageRepo extends CrudRepository <ReplyMessage, Long> {

    @Query(value = "SELECT * FROM reply_message rm WHERE rm.main_message_id = ?1", nativeQuery = true)
    List<ReplyMessage> findForMessage(Integer message_id);

}
