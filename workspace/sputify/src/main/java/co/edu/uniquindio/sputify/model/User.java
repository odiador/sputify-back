package co.edu.uniquindio.sputify.model;


import co.edu.uniquindio.sputify.structures.lists.CircularList;
import co.edu.uniquindio.sputify.structures.lists.LinkedList;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class User {
	@Setter(value = AccessLevel.PRIVATE)
	@Include
	private String username;
	private String email;
	private String password;
	@ToString.Exclude
	private LinkedList<Song> lstSongs;

	@Builder
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.lstSongs = new CircularList<Song>();
	}

}
