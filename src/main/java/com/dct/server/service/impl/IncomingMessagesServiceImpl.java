package com.dct.server.service.impl;

import com.dct.server.dao.IncomingMessagesDAO;
import com.dct.server.model.IncomingMessages;
import com.dct.server.service.IncomingMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stukolov_m on 18.05.2015.
 */
@Service("incomingMessagesService")
public class IncomingMessagesServiceImpl implements IncomingMessagesService {

    @Autowired
    IncomingMessagesDAO incomingMessagesDAO;

    @Override
    public IncomingMessages search(IncomingMessages incomingMessages) {
        return incomingMessagesDAO.search(incomingMessages);
    }

    @Override
    public List<IncomingMessages> findAll() {
        return incomingMessagesDAO.findAll();
    }

    @Override
    public List<IncomingMessages> findOpenedMessages() {
        return incomingMessagesDAO.findOpenedMessages();
    }

    @Override
    public void save(IncomingMessages incomingMessages) {
        incomingMessagesDAO.save(incomingMessages);
    }

    @Override
    public void update(IncomingMessages incomingMessages) {
        incomingMessagesDAO.update(incomingMessages);
    }

    @Override
    public void delete(IncomingMessages incomingMessages) {
        incomingMessagesDAO.delete(incomingMessages);
    }
}
