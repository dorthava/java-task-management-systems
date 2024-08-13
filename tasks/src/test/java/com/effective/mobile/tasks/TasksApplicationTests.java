package com.effective.mobile.tasks;

import com.effective.mobile.tasks.dto.*;
import com.effective.mobile.tasks.models.Priority;
import com.effective.mobile.tasks.models.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class TasksApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void SignUpError() throws Exception {
		SignUpRequest request = new SignUpRequest("test", "test@gmail.com", "qwerty123456");
		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-up")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isBadRequest());
	}

	@Test
	void SignInSuccess() throws Exception {
		SignInRequest request = new SignInRequest("test@gmail.com", "qwerty123456");
		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/auth/sign-in")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "test@gmail.com", password = "qwerty123456")
	void createTaskSuccess() throws Exception {
		String title = "title";
		String description = "description";
		String status = Status.PENDING.toString();
		String priority = Priority.LOW.name();
		String assignee = "test";
		CreateTaskRequest request = new CreateTaskRequest(title, description, status, priority, assignee);

		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/task/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "test@gmail.com", password = "qwerty123456")
	void deleteTaskError() throws Exception {
		DeleteTaskRequest request = new DeleteTaskRequest(-1L);
		String jsonRequest = objectMapper.writeValueAsString(request);
		mockMvc.perform(MockMvcRequestBuilders.delete("/task/delete")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(username = "test@gmail.com", password = "qwerty123456")
	void findTaskSuccess() throws Exception {
		String assignee = "test";
		FindTaskRequest request = new FindTaskRequest(assignee);
		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.get("/task/find")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "test@gmail.com", password = "qwerty123456")
	void updateTaskSuccess() throws Exception {
		UpdateTaskRequest request = new UpdateTaskRequest(1L, "test_title", null, null, null, null);
		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/task/update")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "test2@gmail.com", password = "qwerty123456")
	void updateTaskNoAuthorError() throws Exception {
		UpdateTaskRequest request = new UpdateTaskRequest(1L, "no_title", null, null, null, null);
		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/task/update")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(username = "test2@gmail.com", password = "qwerty123456")
	void updateStatusTaskSuccess() throws Exception {
		UpdateStatusTaskRequest request = new UpdateStatusTaskRequest("IN_PROGRESS");
		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.patch("/task/update-status/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "test3@gmail.com", password = "qwerty123456")
	void updateStatusWrongUserTaskError() throws Exception {
		UpdateStatusTaskRequest request = new UpdateStatusTaskRequest("IN_PROGRESS");
		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.patch("/task/update-status/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isNotFound());
	}
}
