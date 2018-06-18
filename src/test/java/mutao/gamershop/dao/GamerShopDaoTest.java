package mutao.gamershop.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import mutao.gamershop.model.GamerShopModel;

public class GamerShopDaoTest extends AbstractRepositoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private GamerShopDAO gamershopDao;
    private GamerShopModel gamershopModel;

    @Before
    public void setUp() throws Exception {

        gamershopModel = new GamerShopModel();
        gamershopModel.setName("Mouse Razer");
        gamershopModel.setDescription("Mouse com 1000000 dpi");

        gamershopDao = new GamerShopDAO(em);

        /*
		 * Insere ao início de cada testes
         */
        gamershopDao.insert(gamershopModel);

    }

    @After
    public void tearDown() throws Exception {
        gamershopDao.resetTable(GamerShopModel.class);
        em.clear();

    }

    @Test
    public void testaInsert() {
        assertEquals("Não foi inserido", "Mouse Razer", gamershopDao.get(GamerShopModel.class, 1L).getName());

    }

    @Test
    public void testaUpdate() {

        gamershopModel.setName("Update para Teste");

        gamershopModel = gamershopDao.update(gamershopModel);

        assertEquals("Não pode ser atualizado", "Update para Teste", gamershopDao.get(GamerShopModel.class, 1L).getName());

    }

    @Test
    public void testaGet() {
        assertEquals("Não foi possível buscar.", "Mouse Razer", gamershopDao.get(GamerShopModel.class, 1L).getName());
    }

}
