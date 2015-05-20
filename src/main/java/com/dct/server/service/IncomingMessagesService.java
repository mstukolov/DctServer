package com.dct.server.service;

import com.dct.server.model.IncomingMessages;

import java.util.List;

/**
 * Created by stukolov_m on 18.05.2015.
 */
public interface IncomingMessagesService {
    IncomingMessages search(IncomingMessages incomingMessages);

    List<IncomingMessages> findAll();

    List<IncomingMessages> findOpenedMessages();

    void save(IncomingMessages incomingMessages);

    void update(IncomingMessages incomingMessages);

    void delete(IncomingMessages incomingMessages);
}
