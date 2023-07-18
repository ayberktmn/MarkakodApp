package markakodapp.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.ayberk.markakodapp.R;

public class PersonContractFragmentDirections {
  private PersonContractFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionPersonContractFragmentToProfileFragment() {
    return new ActionOnlyNavDirections(R.id.action_personContractFragment_to_profileFragment);
  }
}
