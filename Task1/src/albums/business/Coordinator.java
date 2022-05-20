package albums.business;

import albums.data.DAO;
import albums.data.SimpleDAOImplementation;

public class Coordinator {

    private DAO dao;

    public Coordinator() {

        dao = new SimpleDAOImplementation();

    }

    public Album[] DataLoader() {

        return dao.DataLoader();

    }

}
