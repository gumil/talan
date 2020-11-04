//
//  ContentView.swift
//  talan
//
//  Created by Miguel Panelo on 09/07/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import SwiftUI
import SharedClient

struct ContentView: View {
    @State
    private var wrapper =
        ComponentHolderWrapper {
            AWRootKt.AWRoot(componentContext: $0, appComponent: MainAppComponent())
    }
    
    var body: some View {
        RootView(wrapper.componentHolder.component!.model)
            .onAppear { LifecycleRegistryExtKt.resume(self.wrapper.componentHolder.lifecycle) }
            .onDisappear { LifecycleRegistryExtKt.stop(self.wrapper.componentHolder.lifecycle) }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

class ComponentHolderWrapper<T: AnyObject> {
    let componentHolder: ComponentHolder<T>
    
    init(componentFactory: @escaping (DecomposeComponentContext) -> T) {
        self.componentHolder = ComponentHolder(factory: componentFactory)
        componentHolder.doInit()
    }
    
    deinit {
        componentHolder.deInit()
    }
}
