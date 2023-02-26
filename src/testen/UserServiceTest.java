package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Medewerker;
import domein.User;
import repository.UserDTO;
import repository.UserDao;
import service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest
{

	@Mock
	private UserDao userRepoMock;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	void aanmelden()
	{


		String emailAdress = "test@example.com";
        String password = "password123!!";
        User user = new Medewerker("John", "Doe", emailAdress, password, "adres", "0123456789", 1, "admin");
        
        Mockito.when(userRepoMock.getMedewerkerByEmailAdress(emailAdress)).thenReturn(user);

        UserDTO userDTO = userService.aanmelden(emailAdress, password);

        Assertions.assertEquals(user.getVoornaam(), userDTO.getVoornaam());
        Assertions.assertEquals(user.getFamilienaam(), userDTO.getFamilienaam());
        Assertions.assertEquals(user.getEmail(), userDTO.getEmail());
//        Assertions.assertEquals(user.getAdres(), userDTO.getAdres());
//        Assertions.assertEquals(user.getTelefoonnummer(), userDTO.getTelefoonnummer());
//        Assertions.assertEquals(user.getPersoneelsNr(), userDTO.getPersoneelsNr());
//        Assertions.assertEquals(((Medewerker) user).getFunctie(), ((MedewerkerDTO) userDTO).getFunctie());
	}

}
