package markakodapp.Module;

import com.ayberk.markakodapp.Module.AppModule;

import javax.annotation.processing.Generated;

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
public final class AppModule_GetRetroInstanceFactory implements Factory<Retrofit> {
  private final AppModule module;

  public AppModule_GetRetroInstanceFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public Retrofit get() {
    return getRetroInstance(module);
  }

  public static AppModule_GetRetroInstanceFactory create(AppModule module) {
    return new AppModule_GetRetroInstanceFactory(module);
  }

  public static Retrofit getRetroInstance(AppModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.getRetroInstance());
  }
}
