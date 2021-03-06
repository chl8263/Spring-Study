package me.ewan.springdata;

import me.ewan.springdata.webDomain.WebPost;
import me.ewan.springdata.webDomain.WebPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebPostTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebPostRepository webPostRepository;

    @Test
    public void getPost() throws Exception {

        WebPost webPost = new WebPost();
        webPost.setTitle("jpa");
        webPostRepository.save(webPost);

        mockMvc.perform(get("/post/" + webPost.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
    }

    @Test
    public void getPosts() throws Exception {
        WebPost webPost = new WebPost();
        webPost.setTitle("jpa");
        webPostRepository.save(webPost);

        mockMvc.perform(get("/posts/")
//                        .param("page", "0")
//                        .param("size", "10")
//                        .param("sort", "created,desc")
//                        .param("sort", "title")
                    )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
