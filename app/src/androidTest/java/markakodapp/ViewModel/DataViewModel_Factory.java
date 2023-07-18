package markakodapp.ViewModel;

import com.ayberk.markakodapp.Retrofit.RetrofitRepository;
import com.ayberk.markakodapp.ViewModel.DataViewModel;

import javax.annotation.processing.Generated;
import javax.inject.Provider;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DataViewModel_Factory implements Factory<DataViewModel> {
  private final Provider<RetrofitRepository> repositoryProvider;

  public DataViewModel_Factory(Provider<RetrofitRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DataViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static DataViewModel_Factory create(Provider<RetrofitRepository> repositoryProvider) {
    return new DataViewModel_Factory(repositoryProvider);
  }

  public static DataViewModel newInstance(RetrofitRepository repository) {
    return new DataViewModel(repository);
  }
}
