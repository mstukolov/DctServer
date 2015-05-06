package com.dct.server.dao.impl;

import com.dct.server.dao.DocumentDAO;
import com.dct.server.dao.DocumentLinesDAO;
import com.dct.server.dao.impl.util.SearchUtil;
import com.dct.server.model.Document;
import com.dct.server.model.DocumentHeader;
import com.dct.server.model.DocumentLines;
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

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by stukolov_m on 30.04.2015.
 */
@Transactional
@Repository("documentLinesDAO")
public class DocumentLinesDAOImpl implements DocumentLinesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private FullTextSession fullTextSession;

    @Override
    public DocumentLines search(DocumentLines line) {
        return null;
    }

    @Override
    public List<DocumentLines> search(Document document) {
        fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        try {
            fullTextSession.createIndexer(DocumentLines.class).startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(DocumentLines.class).get();
        BooleanQuery booleanQuery = new BooleanQuery();

        String docNum = document.getDocNum();
        if (docNum != null) {
            BooleanJunction<BooleanJunction> docNumBJ = queryBuilder.bool();
            docNumBJ.should(queryBuilder.phrase().onField("docRef").sentence(docNum).createQuery());
            booleanQuery.add(docNumBJ.createQuery(), BooleanClause.Occur.MUST);
        }

        List<DocumentLines> result = fullTextSession.createFullTextQuery(booleanQuery, DocumentLines.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return result;
    }

    @Override
    public List<DocumentLines> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from DocumentLines").list();
    }

    @Override
    public void save(DocumentLines line) {
        sessionFactory.getCurrentSession().persist(line);
    }

    @Override
    public void update(DocumentLines line) {
        sessionFactory.getCurrentSession().update(line);
    }

    @Override
    public void delete(DocumentLines line) {
        sessionFactory.getCurrentSession().delete(line);
    }
}
