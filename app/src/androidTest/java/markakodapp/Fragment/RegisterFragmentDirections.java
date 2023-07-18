package markakodapp.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.ayberk.markakodapp.R;

public class RegisterFragmentDirections {
  private RegisterFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRegisterFragmentToContractFragment() {
    return new ActionOnlyNavDirections(R.id.action_registerFragment_to_contractFragment);
  }

  @NonNull
  public static NavDirections actionRegisterFragmentToMainFragment() {
    return new ActionOnlyNavDirections(R.id.action_registerFragment_to_mainFragment);
  }

  @NonNull
  public static NavDirections actionRegisterFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_registerFragment_to_loginFragment);
  }
}
