package socialnetwork.community.dao;


import socialnetwork.community.api.model.PostDto;
import socialnetwork.community.dao.entity.Post;

import java.util.List;

public interface PostDao {

    List<PostDto> getAllPosts();

    List<PostDto> getPostsByContact(Long id);

    List<PostDto> convertPostList(List<Post> list);

    List<PostDto> getFriendsPosts(String username);
}
