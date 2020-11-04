//
//  ViewExt.swift
//  talan
//
//  Created by Miguel Panelo on 04/11/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import Foundation
import SwiftUI

extension View {
    @ViewBuilder
    func isHidden(_ hidden: Bool) -> some View {
        if hidden {
            self.hidden()
        } else {
            self
        }
    }
}
