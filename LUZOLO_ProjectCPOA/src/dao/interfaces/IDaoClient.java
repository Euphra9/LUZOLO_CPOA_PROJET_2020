package dao.interfaces;

import java.util.List;

import model.metier.*;


public interface IDaoClient extends IDao<Client>{
	List<Client> getAllClients();
}
