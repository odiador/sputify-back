package co.edu.uniquindio.sputify.model;

import java.util.Objects;

import co.edu.uniquindio.sputify.structures.lists.DoublyLinkedList;
import co.edu.uniquindio.sputify.structures.lists.LinkedList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Artist implements Comparable<Artist> {

	private String code;
	@Setter(value = AccessLevel.PRIVATE)
	@EqualsAndHashCode.Include
	private String name;
	private String nationality;
	private Boolean isBand;
	@ToString.Exclude
	private LinkedList<Song> lstSongs;

	@Builder
	public Artist(String code, String name, String nationality, Boolean isBand) {
		this.code = code;
		this.name = name;
		this.nationality = nationality;
		this.isBand = isBand;
		this.lstSongs = new DoublyLinkedList<Song>();
	}

	@Override
	public int compareTo(Artist o) {
		return o.name.compareTo(this.name);
	}

	public boolean addSong(Song song) {
		Objects.requireNonNull(song);
		if (verifySong(song)) return false;
		lstSongs.addTail(song);
		return true;
	}

	private boolean verifySong(Song song) {
		for (Song eachSong : lstSongs) {
			if (eachSong.equals(song)) {
				return true;
			}
		}
		return false;

	}

}
