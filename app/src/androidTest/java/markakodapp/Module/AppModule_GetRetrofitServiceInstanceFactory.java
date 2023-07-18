package markakodapp.Module;

import com.ayberk.markakodapp.Module.AppModule;
import com.ayberk.markakodapp.Retrofit.RetrofitInstance;

import javax.annotation.processing.Generated;
import javax.inject.Provider;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_GetRetrofitServiceInstanceFactory implements Factory<RetrofitInstance> {
  private final AppModule module;

  private final Provider<Retrofit> retrofitProvider;

  public AppModule_GetRetrofitServiceInstanceFactory(AppModule module,
      Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public RetrofitInstance get() {
    return getRetrofitServiceInstance(module, retrofitProvider.get());
  }

  public static AppModule_GetRetrofitServiceInstanceFactory create(AppModule module,
      Provider<Retrofit> retrofitProvider) {
    return new AppModule_GetRetrofitServiceInstanceFactory(module, retrofitProvider);
  }

  public static RetrofitInstance getRetrofitServiceInstance(AppModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(instance.getRetrofitServiceInstance(retrofit));
  }
}
