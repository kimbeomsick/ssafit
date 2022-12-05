package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.BookmarkDao;
import com.ssafy.ssafit.model.dto.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService{

    @Autowired
    private BookmarkDao bookmarkDao;

    @Override
    public List<Bookmark> getBookmarkList(String userId) {
        return bookmarkDao.selectBookmarkList(userId);
    }

    @Transactional
    @Override
    public void addBookmark(Bookmark bookmark) {
        bookmarkDao.insertBookmark(bookmark);
    }

    @Transactional
    @Override
    public void removeBookmark(Bookmark bookmark) {
        bookmarkDao.deleteBookmark(bookmark);
    }
}
