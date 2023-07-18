package markakodapp;

import com.ayberk.markakodapp.HiltApplication;

import javax.annotation.processing.Generated;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = HiltApplication.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
@Generated("dagger.hilt.android.processor.internal.androidentrypoint.InjectorEntryPointGenerator")
public interface HiltApplication_GeneratedInjector {
  void injectHiltApplication(HiltApplication hiltApplication);
}
