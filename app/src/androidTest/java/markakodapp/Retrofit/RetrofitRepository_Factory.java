package markakodapp.Retrofit;

import com.ayberk.markakodapp.Retrofit.RetrofitInstance;
import com.ayberk.markakodapp.Retrofit.RetrofitRepository;

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
public final class RetrofitRepository_Factory implements Factory<RetrofitRepository> {
  private final Provider<RetrofitInstance> retrofitServiceInstanceProvider;

  public RetrofitRepository_Factory(Provider<RetrofitInstance> retrofitServiceInstanceProvider) {
    this.retrofitServiceInstanceProvider = retrofitServiceInstanceProvider;
  }

  @Override
  public RetrofitRepository get() {
    return newInstance(retrofitServiceInstanceProvider.get());
  }

  public static RetrofitRepository_Factory create(
      Provider<RetrofitInstance> retrofitServiceInstanceProvider) {
    return new RetrofitRepository_Factory(retrofitServiceInstanceProvider);
  }

  public static RetrofitRepository newInstance(RetrofitInstance retrofitServiceInstance) {
    return new RetrofitRepository(retrofitServiceInstance);
  }
}
