package com.dct.server.dao.impl;

import com.dct.server.dao.DocumentDAO;
import com.dct.server.dao.impl.util.SearchUtil;
import com.dct.server.model.Document;
import com.dct.server.model.DocumentHeader;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.event.DocumentEvent;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
@Transactional
@Repository("documentDAO")
public class DocumentDAOImpl implements DocumentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private FullTextSession fullTextSession;

    @Override
    public DocumentHeader search(Document document) {
        fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        try {
            fullTextSession.createIndexer(DocumentHeader.class).startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(DocumentHeader.class).get();
        BooleanQuery booleanQuery = new BooleanQuery();

        String docNum = document.getDocNum();
        String shopIndex = document.getShopindex();

        if (docNum != null) {
            BooleanJunction<BooleanJunction> docNumBJ = queryBuilder.bool();
            docNumBJ.should(queryBuilder.phrase().onField("docNum").sentence(docNum).createQuery());
            booleanQuery.add(docNumBJ.createQuery(), BooleanClause.Occur.MUST);
        }
        if (shopIndex != null) {
            BooleanJunction<BooleanJunction> shopindexBJ = queryBuilder.bool();
            shopindexBJ.should(queryBuilder.phrase().onField("shopindex").sentence(shopIndex).createQuery());
            booleanQuery.add(shopindexBJ.createQuery(), BooleanClause.Occur.MUST);
        }

        List<DocumentHeader> result = fullTextSession.createFullTextQuery(booleanQuery, DocumentHeader.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        if(result.size() > 0)  return result.get(0);
        else return null;
    }

    @Override
    public List<DocumentHeader> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Document").list();
    }

    @Override
    public void create(DocumentHeader document) {
            sessionFactory.getCurrentSession().persist(document);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().refresh(document);
    }

    @Override
    public void update(DocumentHeader document) {
        sessionFactory.getCurrentSession().update(document);
    }

    @Override
    public void delete(DocumentHeader document) {
        sessionFactory.getCurrentSession().delete(document);
    }

    public void deleteDocumentLines(Document document){
        System.out.println("Delete lines from document= " + document.getDocNum());
    }
}
