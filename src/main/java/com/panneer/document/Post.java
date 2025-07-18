package com.panneer.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    private String id;

    private Long userId;             // Reference to PostgreSQL User ID
    private String imageUrl;         // URL or blob reference
    private String caption;
    private List<String> hashtags;   // Optional tags
    private Instant createdAt;
}