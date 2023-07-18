package markakodapp.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.ayberk.markakodapp.R;

public class ContractFragmentDirections {
  private ContractFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionContractFragmentToRegisterFragment() {
    return new ActionOnlyNavDirections(R.id.action_contractFragment_to_registerFragment);
  }
}
