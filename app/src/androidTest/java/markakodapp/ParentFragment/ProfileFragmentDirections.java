package markakodapp.ParentFragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.ayberk.markakodapp.R;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileFragmentToSettingsFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_settingsFragment);
  }

  @NonNull
  public static NavDirections actionProfileFragmentToPersonContractFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_personContractFragment);
  }

  @NonNull
  public static NavDirections actionProfileFragmentToAboutUsFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_aboutUsFragment);
  }

  @NonNull
  public static NavDirections actionProfileFragmentToMainActivity() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_mainActivity);
  }
}
