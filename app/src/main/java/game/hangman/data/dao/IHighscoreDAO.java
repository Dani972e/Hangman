package game.hangman.data.dao;

import android.content.Context;

import java.util.List;

import game.hangman.data.dto.HighscoreDTO;

public interface IHighscoreDAO {

    void add(HighscoreDTO dto) throws DALExceptions;

    void read(Context context) throws DALExceptions;

    void update(HighscoreDTO dto) throws DALExceptions;

    void delete(HighscoreDTO dto) throws DALExceptions;

    void save(Context context) throws DALExceptions;

    List<HighscoreDTO> getList() throws DALExceptions;

}
