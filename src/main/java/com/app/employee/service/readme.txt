Package for the application's business service interfaces. The objects of
input / output of this layer can only be DTOs. Converters will be used for mapping
objects when necessary (as long as you are working with the Repository class).

An example of a business interface could be:

import java.util.List;

import com.test.employee.dto.PlayerDTO;
import com.test.employee.exceptions.PlayerExistsConflictException;

public interface ITeamService {

	PlayerDTO getPlayerById(long id);
	
	PlayerDTO getPlayerWithNumber(long number);

	List<PlayerDTO> getPlayers();

	void insert(PlayerDTO player) throws PlayerExistsConflictException;

	void update(PlayerDTO player);

    void delete(long id);

    boolean isUserExist(long id);

}



@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TeamServiceImpl implements ITeamService {

	private final PlayerMapper playerMapper;

	private final ITeamRepository teamRepository;
	private final ITeamReadRepository teamReadRepository;

	@Override
	public List<PlayerDTO> getPlayers() {
		if(log.isInfoEnabled()){
			log.info("Recuperando la lista de usuarios desde el servicio");
		}
		return playerMapper.playerToPlayerDto(teamReadRepository.findAll());
	}

	@Override
    public PlayerDTO getPlayerWithNumber(long number) {
		if(log.isInfoEnabled()){
			log.info("Recuperando el usuario con number "+ number + " desde el servicio");
		}
        return playerMapper.playerToPlayerDto(teamReadRepository.findByNumber(number));
	}

	@Override
	public void insert(PlayerDTO player) throws PlayerExistsConflictException {
		if (getPlayerWithNumber(player.getNumber()) != null ) {
			if (log.isWarnEnabled()) {
				log.error("Imposible la creacion. El usuario con el nombre  {}, ya existe.", player.getName());
			}
			throw new PlayerExistsConflictException(
					"Imposible la creacion. El usuario con el nombre " + player.getName() + ", ya existe.");
		}
		teamRepository.saveAndFlush(playerMapper.playerDtoToPlayer(player));
	}

	@Override
	public void update(PlayerDTO player) {
		teamRepository.saveAndFlush(playerMapper.playerDtoToPlayer(player));
	}

	@Override
    public PlayerDTO getPlayerById(long id) {
		
//        return playerMapper.playerToPlayerDto(teamReadRepository.getOne(id)); No uses
		return playerMapper.playerToPlayerDto(teamReadRepository.findById(id).get());
    }
    
    @Override
	public void delete(long id) {
		teamRepository.deleteById(id);
	}

	@Override
	public boolean isUserExist(long id) {
		return teamRepository.existsById(id);
	}
}

