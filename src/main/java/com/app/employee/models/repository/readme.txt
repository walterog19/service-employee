Package for jpa interfaces (spring-data-jpa is used to automatically implement all methods)
that contain all the operations that can be carried out against the database.
This package works with Entity classes from the model package.

A repository class could be:

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employee.domain.Player;

public interface ITeamRepository extends JpaRepository<Player, Long> {

	Player findByNumber(long number);

}