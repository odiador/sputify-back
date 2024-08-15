package co.edu.uniquindio.sputify.utils;

import java.time.LocalTime;

import co.edu.uniquindio.sputify.model.Artist;
import co.edu.uniquindio.sputify.model.Song;
import co.edu.uniquindio.sputify.model.Song.Genre;
import co.edu.uniquindio.sputify.structures.trees.BinaryTree;
import co.edu.uniquindio.sputify.structures.trees.Tree;

public class TextUtils {

	private static enum OBJECT_TYPE {
		ARTISTS("#Artistas"), SONGS("#Canciones");

		private OBJECT_TYPE(String text) {
			this.text = text;
		}

		private String text;
	}

	public static Tree<Artist> getLinesByString(String string) {
		String[] array = string.split("\n");
		OBJECT_TYPE type = null;
		Tree<Artist> lstArtists = new BinaryTree<Artist>();
		forLineas: for (String line : array) {
			if (line.isEmpty())
				continue;
			if (line.startsWith("#"))
				for (OBJECT_TYPE tipo : OBJECT_TYPE.values()) {
					if (line.equals(tipo.text)) {
						type = tipo;
						continue forLineas;
					}
				}
			if (type == null)
				continue;
			switch (type) {
			case ARTISTS -> addArtist(lstArtists, line);
			case SONGS -> addSong(lstArtists, line);
			}
		}
		return lstArtists;

	}

	private static void addSong(Tree<Artist> lstArtists, String line) {
		String[] split = line.split(";");
		LocalTime time = getSongTime(split[4]);
		Song song = Song.builder().name(split[1]).albumName(split[2]).year(Integer.parseInt(split[3])).duration(time)
				.genre(Genre.genreOf(split[5])).url(split[6]).build();

		for (Artist artist : lstArtists)
			if (artist.getName().equals(split[0])) {
				artist.addSong(song);
				return;
			}
	}

	private static LocalTime getSongTime(String unparsedTime) {
		String[] split = unparsedTime.split(":");
		LocalTime time = null;
		if (split.length == 3) {
			int segs = Integer.parseInt(split[2]);
			int mins = Integer.parseInt(split[1]) + segs / 60;
			int hours = Integer.parseInt(split[0]) + mins / 60;
			time = LocalTime.of(hours, mins % 60, segs % 60);
		} else if (split.length == 2) {
			int segs = Integer.parseInt(split[1]);
			int mins = Integer.parseInt(split[0]) + segs / 60;
			time = LocalTime.of(mins / 60, mins % 60, segs % 60);
		}
		return time;
	}

	private static void addArtist(Tree<Artist> lstArtists, String line) {
		String[] split = line.split(";");
		Artist newArtist = Artist.builder().code(split[0]).name(split[1]).nationality(split[2])
				.isBand(Boolean.parseBoolean(split[3])).build();
		lstArtists.insert(newArtist);
	}
}
