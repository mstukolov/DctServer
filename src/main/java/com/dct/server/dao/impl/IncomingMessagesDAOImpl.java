package com.dct.server.dao.impl;

import com.dct.server.dao.IncomingMessagesDAO;
import com.dct.server.model.IncomingMessages;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by stukolov_m on 18.05.2015.
 */
@Transactional
@Repository("incomingMessagesDAO")
public class IncomingMessagesDAOImpl implements IncomingMessagesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private FullTextSession fullTextSession;

    @Override
    public IncomingMessages search(IncomingMessages incomingMessages) {
        fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        try {
            fullTextSession.createIndexer(IncomingMessages.class).startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(IncomingMessages.class).get();
        BooleanQuery booleanQuery = new BooleanQuery();

        if (incomingMessages.getPackageNo() != null) {
            BooleanJunction<BooleanJunction> docNumBJ = queryBuilder.bool();
            docNumBJ.should(queryBuilder.phrase().onField("PackageNo").sentence(incomingMessages.getPackageNo().toString()).createQuery());
            booleanQuery.add(docNumBJ.createQuery(), BooleanClause.Occur.MUST);
        }
        List<IncomingMessages> result = fullTextSession.createFullTextQuery(booleanQuery, IncomingMessages.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        if(result.size() > 0)  return result.get(0);
        else return null;
    }

    @Override
    public List<IncomingMessages> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from IncomingMessages").list();
    }

    @Override
    public List<IncomingMessages> findOpenedMessages() {
        fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        try {
            fullTextSession.createIndexer(IncomingMessages.class).startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(IncomingMessages.class).get();
        BooleanQuery booleanQuery = new BooleanQuery();
        BooleanJunction<BooleanJunction> statusBJ = queryBuilder.bool();
        statusBJ.should(queryBuilder.phrase().onField("Status").sentence("0").createQuery());
        booleanQuery.add(statusBJ.createQuery(), BooleanClause.Occur.MUST);
        List<IncomingMessages> result = fullTextSession.createFullTextQuery(booleanQuery, IncomingMessages.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return result;
    }

    @Override
    public void save(IncomingMessages incomingMessages) {
        sessionFactory.getCurrentSession().persist(incomingMessages);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(incomingMessages);
    }

    @Override
    public void update(IncomingMessages incomingMessages) {
        sessionFactory.getCurrentSession().update(incomingMessages);
    }

    @Override
    public void delete(IncomingMessages incomingMessages) {
        sessionFactory.getCurrentSession().delete(incomingMessages);
    }
}
