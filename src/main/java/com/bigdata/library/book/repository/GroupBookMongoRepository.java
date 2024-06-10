package com.bigdata.library.book.repository;

import com.bigdata.library.book.domain.GroupedBookDocument;
import com.bigdata.library.book.repository.dto.ShelfAuthors;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface GroupBookMongoRepository extends MongoRepository<GroupedBookDocument, GroupedBookDocument.BookId> {

    @Aggregation(pipeline = {
            "{ $match: { totalBooks: { $gte: 30 } } }",
            "{ $group: { _id: '$_id.shelf', authors: { $push: { name: '$_id.author', averageRating: '$averageRating', totalBooks: '$totalBooks' } } } }",
            "{ $project: { authors: { $slice: ['$authors', 3] } } }",
    })
    List<ShelfAuthors> getTop3AuthorsPerShelf();
}
