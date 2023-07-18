package markakodapp.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.ayberk.markakodapp.R;

public class AboutUsFragmentDirections {
  private AboutUsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionAboutUsFragmentToProfileFragment() {
    return new ActionOnlyNavDirections(R.id.action_aboutUsFragment_to_profileFragment);
  }
}
