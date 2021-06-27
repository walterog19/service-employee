
package for controller test classes. An example could be:

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.app.employee.dto.PlayerDTO;
import com.app.employee.exceptions.PlayerExistsConflictException;
import com.app.employee.helpers.TeamServiceHelper;
import com.app.employee.service.ITeamService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestApiController.class)
public class RestApiControllerTest {

	@MockBean
	private ITeamService teamService;

	@Autowired
	private MockMvc mockMvc;

	protected static ObjectMapper om = new ObjectMapper();

	@Test
	public void testListAllUsersOK() throws Exception {

		when(teamService.getPlayers()).thenReturn(TeamServiceHelper.createPlayersDTO());

		mockMvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testListAllUsersNoContent() throws Exception {

		when(teamService.getPlayers()).thenReturn(TeamServiceHelper.createPlayersDTOEmpty());

		mockMvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	public void testGetUserOK() throws Exception {

		when(teamService.getPlayerById(1)).thenReturn(TeamServiceHelper.createPlayerDTO());

		mockMvc.perform(get("/api/user/{id}", 1).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("Player 1")));
	}

	@Test
	public void testGetUserKO() throws Exception {

		when(teamService.getPlayerById(1)).thenReturn(null);

		mockMvc.perform(get("/api/user/{id}", 1).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("User with id 1 not found")));
	}

	@Test
	public void testCreateUserOK() throws Exception {

		mockMvc.perform(post("/api/user").content(om.writeValueAsString(TeamServiceHelper.createPlayerDTO()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(containsString("Player 1")));

		verify(teamService, times(1)).insert(any(PlayerDTO.class));
	}

	@Test
	public void testCreateUserKO() throws Exception {
		doThrow(new PlayerExistsConflictException("Conflicto al crear el usuario")).when(teamService).insert(any());

		mockMvc.perform(post("/api/user").content(om.writeValueAsString(TeamServiceHelper.createPlayerDTO()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isConflict())
				.andExpect(content().string(containsString("Conflicto al crear el usuario")));
	}

	@Test
	public void testUpdateUserOK() throws Exception {

		when(teamService.getPlayerById(1)).thenReturn(TeamServiceHelper.createPlayerDTO());

		mockMvc.perform(put("/api/user/{id}", 1).content(om.writeValueAsString(TeamServiceHelper.createPlayerDTO()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Player 1")));
	}

	@Test
	public void testUpdateUserKO() throws Exception {

		when(teamService.getPlayerById(1)).thenReturn(null);

		mockMvc.perform(put("/api/user/{id}", 1).content(om.writeValueAsString(TeamServiceHelper.createPlayerDTO()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("Imposible actualizar. Usuario con id 1 no se encontro.")));
	}

	@Test
	public void testDeleteUserOK() throws Exception {

		when(teamService.getPlayerById(1)).thenReturn(TeamServiceHelper.createPlayerDTO());

		mockMvc.perform(delete("/api/user/{id}", 1).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isAccepted()).andExpect(content().string(containsString("Player 1")));
	}

	@Test
	public void testDeleteUserKO() throws Exception {

		when(teamService.getPlayerById(1)).thenReturn(null);

		mockMvc.perform(delete("/api/user/{id}", 1).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isInternalServerError()).andExpect(
						content().string(containsString("No es posible eliminarlo. Usuario con id 1 no encontrado.")));
	}
}
