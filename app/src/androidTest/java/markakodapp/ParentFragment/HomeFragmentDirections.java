package markakodapp.ParentFragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.ayberk.markakodapp.R;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionHomeFragmentToProfileFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_profileFragment);
  }
}
