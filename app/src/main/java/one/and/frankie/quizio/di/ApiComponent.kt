package one.and.frankie.quizio.di

import dagger.Component
import one.and.frankie.quizio.model.QAsService
import one.and.frankie.quizio.viewmodel.CardsViewModel

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: QAsService)

    fun inject(viewModel: CardsViewModel)
}